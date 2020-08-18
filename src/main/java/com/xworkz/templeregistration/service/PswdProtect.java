package com.xworkz.templeregistration.service;

public interface PswdProtect {

	byte[] encript(byte[] data);
	byte[] decript(byte[] data);
}
