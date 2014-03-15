package com.martin.networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class StreamServer {

	public static void main(String[] args) {
		
		final int backlog = 10;
		final int port = 1234;
		
		try {
			@SuppressWarnings("resource")
			ServerSocket socket = new ServerSocket(port, backlog);
			for (;;) {
				System.out.println("Waiting for connection...");
				Socket sockConnected = socket.accept();
				System.out.println("Connected to " + sockConnected);
				PrintStream out = new PrintStream(sockConnected.getOutputStream(), true, "UTF-8");
				out.println("Well, that's me in some charset. Hope you're having fun! Even with Ümläutén");
				TimeUnit.SECONDS.sleep(1);
				out.println("Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit. Proin mollis auctor dolor, eu varius metus laoreet eu. Cras at vulputate justo. Sed enim tortor, iaculis id blandit vitae, mattis ut leo. Quisque elementum purus in pellentesque imperdiet. Quisque a vulputate sem. Sed risus sapien, vulputate vitae cursus in, luctus ut massa. Nullam tristique dui at mauris convallis, ac iaculis ante sodales. Mauris bibendum posuere diam, vitae vestibulum nibh gravida eu. Suspendisse dictum, nisi vitae elementum accumsan, arcu lacus tempor est, a fringilla lectus turpis a sem. Suspendisse aliquet ut turpis sodales bibendum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis semper turpis tortor, eu aliquet quam feugiat non. Duis sollicitudin, nibh id facilisis condimentum, eros velit laoreet leo, id pharetra tortor nisl eget est. Pellentesque sodales, ligula sit amet lacinia tristique, urna arcu ultrices diam, venenatis tincidunt velit diam et ligula. Vivamus ullamcorper turpis ante, vel tristique nulla cursus non. Etiam dictum mollis semper. In nulla sem, tristique ut accumsan in, dapibus sed nisl. Aenean bibendum eleifend felis eget porta. Nullam posuere diam et felis porttitor, dapibus ultrices ipsum tempor. Mauris tempus eros ipsum. Fusce at dapibus dolor, vel pulvinar orci. Integer condimentum vulputate urna, eget scelerisque massa sollicitudin sit amet. Vivamus et sem ac massa pulvinar faucibus vehicula sit amet lectus. Vestibulum tincidunt sapien eu dui consectetur molestie. Cras nulla metus, vulputate eget odio eget, suscipit porta mi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In fermentum placerat ante. Nulla sollicitudin dolor nulla, non auctor nunc laoreet vitae. Pellentesque cursus lacus quis lectus mollis viverra. Nullam faucibus erat eget adipiscing vestibulum. Sed fermentum enim in egestas iaculis.Proin ut dui imperdiet, aliquam leo a, adipiscing purus. Praesent vehicula fermentum pulvinar. Aenean euismod nunc orci, et suscipit felis imperdiet orci aliquam.");
				sockConnected.close();
			}
		}
		catch (IOException e) {
			System.err.println(e);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

}
