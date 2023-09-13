package webdriver.phuong.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.Gmail.Users.Messages.List;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class GmailPageAccess {
	
	protected Gmail service;

	public GmailPageAccess() throws Exception {
		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
		service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
				.setApplicationName("Railway").build();
	}

	public Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
			throws IOException {
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
				new InputStreamReader(GmailPageAccess.class.getResourceAsStream("/credentials.json")));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
				clientSecrets, Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM))
				.setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile())).setAccessType("offline")
				.build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9999).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public String getURL(String user) throws Exception {
		List request = service.users().messages().list(user).setQ("from:" + "thanhletraining03@gmail.com");

		ListMessagesResponse messageResponse = request.execute();
		request.setPageToken(messageResponse.getNextPageToken());
				
		String messageID = messageResponse.getMessages().get(0).getId();
		
		Message message = service.users().messages().get(user, messageID).execute();
	
		java.util.List<String> txt = extractUrls(message.getSnippet());
		
		return txt.get(0);
	}
	
	public java.util.List<String> extractUrls(String text)
	{
	    java.util.List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    Matcher urlMatcher = pattern.matcher(text);

	    while (urlMatcher.find())
	    {
	        containedUrls.add(text.substring(urlMatcher.start(0),
	                urlMatcher.end(0)));
	    }
	    return containedUrls;
	}
	
	
}
