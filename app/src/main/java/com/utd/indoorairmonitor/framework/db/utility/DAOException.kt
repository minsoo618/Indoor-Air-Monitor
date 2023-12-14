package com.utd.indoorairmonitor.framework.db.utility

class DAOException : Exception {
    constructor(msg: String?) : super(msg) {}
    constructor(msg: String?, ex: Throwable?) : super(msg, ex) {}
}