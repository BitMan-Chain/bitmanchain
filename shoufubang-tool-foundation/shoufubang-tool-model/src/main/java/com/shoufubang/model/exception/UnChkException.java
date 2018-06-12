package com.shoufubang.model.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Base Un-Checked exception.
 * 方便传递异常，这类异常会在TranscationFilter统一处理
 */

public class UnChkException extends RuntimeException {

    private static final long serialVersionUID = -2501214594056775079L;

    private Throwable cause;

	public UnChkException(String msg) {
		super(msg);
	}

	public UnChkException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}

	public Throwable getCause() {
		return (this.cause == null ? null : this.cause);
	}

	public String getMessage() {
		String message = super.getMessage();
		Throwable cause = getCause();
		if (cause != null) {
			message = message + "-->" + cause.getMessage();
		}
		return message;
	}

	public void printStackTrace(PrintStream ps) {
		if (getCause() == null) {
			super.printStackTrace(ps);

		} else {
			ps.println(this);
			getCause().printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		if (getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			getCause().printStackTrace(pw);
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}
}
