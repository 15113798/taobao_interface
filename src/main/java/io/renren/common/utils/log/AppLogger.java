package io.renren.common.utils.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class AppLogger implements Logger {
    private Logger log;
    
    public AppLogger(Logger log) {
        this.log = log;
    }

    @Override
    public String getName() {
        return this.log.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        this.log.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        this.log.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        this.log.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        this.log.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        this.log.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return this.log.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        this.log.trace(marker, msg);
        
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        this.log.trace(marker, format, arg);
        
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        this.log.trace(marker, format, arg1, arg2);
        
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        this.trace(marker, format, argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        this.trace(marker, msg, t);
        
    }

    @Override
    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        this.log.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        this.log.debug(format, arg);
        
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        this.log.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        this.log.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        this.log.debug(msg, t);
        
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return this.log.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        this.log.debug(marker, msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        this.log.debug(marker, format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        this.log.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        this.log.debug(marker, format, arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        this.log.debug(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        this.log.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        this.log.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        this.log.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        this.log.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        this.log.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void info(Marker marker, String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isWarnEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void warn(String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(String format, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(String format, Object... arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(String msg, Throwable t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void warn(Marker marker, String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isErrorEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void error(String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(String format, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(String format, Object... arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(String msg, Throwable t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void error(Marker marker, String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        
    }
    
    public void startLog() {
        
    }
    
    public void endLog() {
        
    }
}