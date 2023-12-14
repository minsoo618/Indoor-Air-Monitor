package com.utd.indoorairmonitor.framework.asthmaMLModel

import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import com.utd.indoorairmonitor.domain.MLModel
import com.utd.indoorairmonitor.data.MLModelDataSource
import com.utd.indoorairmonitor.framework.ViewModelFactory.application
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class AsthmaMLModel: MLModelDataSource {
    private val mlModel = MLModel(0.0, 0.0)

    override fun getModel() = mlModel
    override fun getResult() = mlModel.result
    override fun getMLOutput2() = mlModel.output2

    // Could be turned into an api call in order to hide the model.
    override fun predictResult(temperature: Double, humidity: Int, pm2_5: Double, pm10_0: Double) {
        val interpreter = makeInterpreter()
        val predictedResult = doInference(interpreter, temperature, humidity, pm2_5, pm10_0)
        val result = predictedResult[0][0].toDouble()
        val formattedResult = Math.round(result).toInt().toDouble()

        mlModel.result = formattedResult
        mlModel.output2 = 0.0

        interpreter.close()
    }

    private fun makeInterpreter(): Interpreter {
        var interpreter: Interpreter?

        val assetManager = application.assets
        val model = loadModelFile(assetManager, "Asthma.tflite")

        val options = Interpreter.Options()
        options.setUseNNAPI(true)

        interpreter = Interpreter(model,options)

        return interpreter
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, filename: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(filename)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun doInference(interpreter : Interpreter, temperature: Double, humidity: Int, pm2_5: Double, pm10_0: Double): Array<FloatArray> {
        val temp = temperature.toFloat()
        val hum =  humidity.toFloat()
        val pm2_5 = pm2_5.toFloat()
        val pm10_0 = pm10_0.toFloat()

        // 4 is for Float byte size
        // 4 is for second tensor shape
        val modelInputSize = 4 * 4
        val mlInput = ByteBuffer.allocateDirect(modelInputSize)
        mlInput.order(ByteOrder.nativeOrder())

        mlInput.putFloat(temp)
                .putFloat(hum)
                .putFloat(pm2_5)
                .putFloat(pm10_0)

        val output = Array(1) { FloatArray(1) }

        interpreter.run(mlInput, output)

        return output
    }
}