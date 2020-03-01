package com.itheima.config;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.itheima.utils.RsaUtils;

@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

	private String pubKeyFile;
	private String priKeyFile;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	@PostConstruct
	public void createRsaKey() throws Exception {
		publicKey = RsaUtils.getPublicKey(pubKeyFile);
		privateKey = RsaUtils.getPrivateKey(priKeyFile);
	}

	public String getPubKeyFile() {
		return pubKeyFile;
	}

	public void setPubKeyFile(String pubKeyFile) {
		this.pubKeyFile = pubKeyFile;
	}

	public String getPriKeyFile() {
		return priKeyFile;
	}

	public void setPriKeyFile(String priKeyFile) {
		this.priKeyFile = priKeyFile;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}
