package com.itheima.config;

import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.itheima.utils.RsaUtils;

@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

	private String pubKeyFile;

	private PublicKey publicKey;

	@PostConstruct
	public void createRsaKey() throws Exception {
		publicKey = RsaUtils.getPublicKey(pubKeyFile);
	}

	public String getPubKeyFile() {
		return pubKeyFile;
	}

	public void setPubKeyFile(String pubKeyFile) {
		this.pubKeyFile = pubKeyFile;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
