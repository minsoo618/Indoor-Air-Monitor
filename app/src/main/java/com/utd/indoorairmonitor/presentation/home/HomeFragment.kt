package com.utd.indoorairmonitor.presentation.home

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.utd.indoorairmonitor.R
import com.utd.indoorairmonitor.databinding.FragmentHomeBinding
import com.utd.indoorairmonitor.framework.ViewModelFactory
import com.utd.indoorairmonitor.presentation.util.*
import kotlinx.android.synthetic.main.activity_setup.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Set fragment title
        (activity as AppCompatActivity).supportActionBar?.title = "Home"

        // Create notification channel
        createChannel(getString(R.string.prediction_notification_channel_id),getString(R.string.prediction_notification_channel_name))

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        // Get view model from provider
        viewModel = ViewModelProvider(this, ViewModelFactory).get(HomeViewModel::class.java)

        setButtonListeners()

        // Observe value changes for the private data field of the view model
        // Reflect the change on view
        setObservables()

        //Polls data from web and predicts using ml model every x amount of time.
        setUpLoops()

        return binding.root
    }
    private fun setObservables() {
        viewModel.pm2_5.observe(viewLifecycleOwner, Observer<Double> { newName ->
            pm25_edit.setText(newName.toString())
        })
        viewModel.pm10_0.observe(viewLifecycleOwner, Observer<Double> { newName ->
            pm10_edit.setText(newName.toString())
        })
        viewModel.temperature.observe(viewLifecycleOwner, Observer<Double> { newName ->
            temp_edit.setText(" " + newName.toString() + "°")
        })
        viewModel.humidity.observe(viewLifecycleOwner, Observer<Int> { newName ->
            hum_edit.setText(newName.toString() + "% Humidity")
        })
        viewModel.weatherID.observe(viewLifecycleOwner, Observer<Int> { newName ->
            // based on weather ID, set image
            if(newName == 800) {
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_sunny_outline))
            } else if (newName > 800) {
                Log.d("Weather img", "cloudy")
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_cloud_outline))
            } else if (newName > 700) { // TODO: Add haze
                Log.d("Weather img", "hazy")
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_sunny_outline))
            } else if (newName > 600) {
                Log.d("Weather img", "snowy")
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_snow_outline))
            } else if (newName > 300) {
                Log.d("Weather img", "rainy")
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_rainy_outline))
            } else if (newName > 200) {
                Log.d("Weather img", "storm")
                weather_img.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.ic_thunderstorm_outline))
            }
        })
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH)
                    .apply {
                        setShowBadge(false)
                    }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.prediction_notification_channel_description)

            // Get notification manager and create channel
            val notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun setButtonListeners(){
        binding.predictButton.setOnClickListener {
            viewModel.setPEFR(pefr_edit.text.toString().toDouble())
            viewModel.predictMLResult()
            val mlResult = viewModel.mlResult.value

            var message: String = "Predicted Value: " + mlResult.toString() + ". "
            message = chooseMessage(mlResult, message)

            val dialog = AlertDialog.Builder(this.requireContext())
            dialog.setTitle("Prediction Results")
            dialog.setMessage(message)
            dialog.setNegativeButton("Close", null)
            dialog.show()
            
            // Refresh the page
            parentFragmentManager.beginTransaction().detach(this).commitNow();
            parentFragmentManager.beginTransaction().attach(this).commitNow();
        }

        binding.pefrInfoButton.setOnClickListener {
            val helpText = "Please enter your peak expiratory flow rate (PEFR) measurement."
            createAlert(helpText, "About PEFR")
        }

        /*binding.zipInfoButton.setOnClickListener {
            val helpText = "Please enter your zip code to get local weather data."
            createAlert(helpText, "About Zip Code")
        }

        binding.monitorIdInfoButton.setOnClickListener {
            val helpText = "Device-ID: A unique code for every sensor.\nIt is printed on the sensor sticker or listed in the shipping confirmation email."
            createAlert(helpText, "About Device ID")
        }*/
    }
    private fun chooseMessage(result: Double?, message: String): String {
        var msg = message
        val peakFlow = viewModel.getPEFR()
        if (result != null) {
            if (result <= peakFlow * .50)
                msg += "WARNING TAKE MEDICATION!"
            else if (result <= peakFlow * .80)
                msg += "Caution, Asthma is getting worse"
            else
                msg += "You are healthy"
        }
        return msg
    }
    private fun createAlert(helpText: String, title: String) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage(helpText)
        val alert = dialogBuilder.create()
        alert.setTitle(title)
        alert.show()
    }

    private fun setUpLoops() {
        viewModel.fetchWeatherData()
        viewModel.fetchAirMonitorData()
        pollData()
        loopPredictFunction()
    }

    //Change this values for the notification and predictions timings
    private val minute: Long = 60000
    private val pollRate : Long = minute * 60    //60 minutes
    private fun pollData() {
        val poll = object : Runnable {
            override fun run() {
                val pref = activity!!.getSharedPreferences("mypref", AppCompatActivity.MODE_PRIVATE)
                viewModel.setAirMonitorID(pref.getString("MIDCode", ""));
                viewModel.setZipCode(pref.getString("zipCode", ""));
                viewModel.setPEFR(pefr_edit.text.toString().toDouble()); //TODO: Add to pref?

                //send notification
                AskPEFR();

                viewModel.fetchWeatherData();
                viewModel.fetchAirMonitorData();

                // TODO: add set image


                Handler(Looper.getMainLooper()).postDelayed(this, pollRate);
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(poll, 5000);
    }
    private fun AskPEFR() {
        notify(requireContext().applicationContext, "Please reply with PEFR value.")
        val reply = recieveInput(requireActivity().intent)
        val pefr = validateReply(reply)

        if (pefr > 0 && pefr < 1000) {
            pefr_edit.setText(pefr.toString())
            viewModel.setPEFR(pefr)
            viewModel.predictMLResult()
            val mlResult = viewModel.mlResult.value
            var message: String = "Predicted Value: " + mlResult.toString() + ". "
            message = chooseMessage(mlResult, message)

            notify(requireContext().applicationContext, message)
        }
    }
    private fun validateReply(reply:String) : Double{
        if(reply.isDigitsOnly() && reply.toDoubleOrNull() != null){
            return reply.toDouble()
        }
        return 0.0
    }

    private fun loopPredictFunction() {
        val poll = object : Runnable {
            override fun run() {
                viewModel.predictMLResult()

                val mlResult = viewModel.mlResult.value
                var message: String = "Predicted Value: " + mlResult.toString() + ". "
                message = chooseMessage(mlResult, message)

                //notify(this@HomeFragment.requireContext(), message)
                Handler(Looper.getMainLooper()).postDelayed(this, pollRate)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(poll, 15000)
    }

    private fun notify(context: Context, message: String) {
        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(message, context)
    }
}
