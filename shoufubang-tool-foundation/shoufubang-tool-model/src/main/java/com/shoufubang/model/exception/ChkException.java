package com.shoufubang.model.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ChkException extends Exception {

	private static final long serialVersionUID = 5567111365902190842L;
	private Throwable cause;

	public ChkException(String msg) {
		super(msg);
	}



	public ChkException(String msg, Throwable ex) {
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
