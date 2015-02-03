package com.freddiemac;

import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class StubHttpProxy {
	
	
	public static void main(String[] args) {
		File f = new File(System.getProperty("user.dir"));
		System.out.println(f.getAbsolutePath());
		WireMockServer server = new WireMockServer(new WireMockConfiguration().port(9999).fileSource(new SingleRootFileSource(f)).proxyVia("bproxy", 8080));
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("c38051","*****".toCharArray());
			}
		});
		server.start();
	}

}
