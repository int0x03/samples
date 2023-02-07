package com.tianxiaohui.logback;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeadLockSample {

	private static final Logger logger = LoggerFactory.getLogger(DeadLockSample.class);

	public static void main(String[] args) {
		System.setOut(new PrintStream(new LogbackOutputStream(logger, false)));
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					logger.info("Example log from {}", DeadLockSample.class.getSimpleName());
					logger.info("from logback - SgVkXp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(G-KaPdSgVkYp3s6v9y$B&E)H@MbQeThWmZq4t7w!z%C*F-JaNdRfUjXn2r5u8x/A?D(G+KbPeShVmYp3s6v9y$B&E)H@McQfTjWnZr4t7w!z%C*F-JaNdRgUkXp2s5v8x/A?D(G+KbPeShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPdSgVkYp3s6v8y/B?E(H+MbQeThWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaP");
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println("from system.out - n2r5u8x/A?D(G+KbPeShVmYp3s6v9y$B&E)H@McQfTjWnZr4t7w!z%C*F-JaNdRgUkXp2s5v8x/A?D(G+KbPeShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPdSgVkYp3s6v8y/B?E(H+MbQeThWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)H+MbQeThWmZq4t7w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8x/A?D(G+KbPeShVmYq3t6v9y$B&E)H@McQfTjWnZr4u7x!z%C*F-JaNdRgUk");
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class LogbackOutputStream extends OutputStream {

	protected static final String LINE_SEPERATOR = System.getProperty("line.separator");
	
	protected Logger log;
	protected boolean isError;

	/**
	 * Used to maintain the contract of {@link #close()}.
	 */
	protected boolean hasBeenClosed = false;

	/**
	 * The internal buffer where data is stored.
	 */
	protected byte[] buf;

	/**
	 * The number of valid bytes in the buffer. This value is always in the
	 * range <tt>0</tt> through <tt>buf.length</tt>; elements
	 * <tt>buf[0]</tt> through <tt>buf[count-1]</tt> contain valid byte
	 * data.
	 */
	protected int count;

	/**
	 * Remembers the size of the buffer for speed.
	 */
	private int bufLength;

	/**
	 * The default number of bytes in the buffer. =2048
	 */
	public static final int DEFAULT_BUFFER_LENGTH = 64;

	/**
	 * Creates the LoggingOutputStream to flush to the given Category.
	 * 
	 * @param log
	 *            the Logger to write to
	 * 
	 * @param isError
	 *            the if true write to error, else info
	 * 
	 * @exception IllegalArgumentException
	 *                if cat == null or priority == null
	 */
	public LogbackOutputStream(Logger log, boolean isError) throws IllegalArgumentException {
		if (log == null) {
			throw new IllegalArgumentException("log == null");
		}

		this.isError = isError;
		this.log = log;
		bufLength = DEFAULT_BUFFER_LENGTH;
		buf = new byte[DEFAULT_BUFFER_LENGTH];
		count = 0;
	}

	/**
	 * Closes this output stream and releases any system resources
	 * associated with this stream. The general contract of
	 * <code>close</code> is that it closes the output stream. A closed
	 * stream cannot perform output operations and cannot be reopened.
	 */
	@Override
	public void close() {
		flush();
		hasBeenClosed = true;
	}

	/**
	 * Writes the specified byte to this output stream. The general contract
	 * for <code>write</code> is that one byte is written to the output
	 * stream. The byte to be written is the eight low-order bits of the
	 * argument <code>b</code>. The 24 high-order bits of <code>b</code> are
	 * ignored.
	 * 
	 * @param b
	 *            the <code>byte</code> to write
	 */
	@Override
	public void write(final int b) throws IOException {
		if (hasBeenClosed) {
			throw new IOException("The stream has been closed.");
		}

		// don't log nulls
		if (b == 0) {
			return;
		}

		// would this be writing past the buffer?
		if (count == bufLength) {
			// grow the buffer
			final int newBufLength = bufLength + DEFAULT_BUFFER_LENGTH;
			final byte[] newBuf = new byte[newBufLength];

			System.arraycopy(buf, 0, newBuf, 0, bufLength);

			buf = newBuf;
			bufLength = newBufLength;
		}

		buf[count] = (byte) b;
		count++;
		
		if (count == bufLength) {
			this.flush();
		}
	}

	/**
	 * Flushes this output stream and forces any buffered output bytes to be
	 * written out. The general contract of <code>flush</code> is that
	 * calling it is an indication that, if any bytes previously written
	 * have been buffered by the implementation of the output stream, such
	 * bytes should immediately be written to their intended destination.
	 */
	@Override
	public void flush() {

		if (count == 0) {
			return;
		}

		// don't print out blank lines; flushing from PrintStream puts out
		// these
		if (count == LINE_SEPERATOR.length()) {
			if (((char) buf[0]) == LINE_SEPERATOR.charAt(0) && ((count == 1) || // <-
					// Unix
					// &
					// Mac,
					// ->
					// Windows
					((count == 2) && ((char) buf[1]) == LINE_SEPERATOR.charAt(1)))) {
				reset();
				return;
			}
		}

		final byte[] theBytes = new byte[count];

		System.arraycopy(buf, 0, theBytes, 0, count);

		if (isError) {
			log.error(new String(theBytes));
		} else {
			log.info(new String(theBytes));
		}

		reset();
	}

	private void reset() {
		// not resetting the buffer -- assuming that if it grew that it
		// will likely grow similarly again
		count = 0;
	}
	
}
