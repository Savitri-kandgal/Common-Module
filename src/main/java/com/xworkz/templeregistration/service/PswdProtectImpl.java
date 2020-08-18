package com.xworkz.templeregistration.service;

public class PswdProtectImpl implements PswdProtect{

	@Override
	public byte[] encript(byte[] data) {
		byte[] enc = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			enc[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
		}
		return enc;
	}

	@Override
	public byte[] decript(byte[] data) {
		byte[] dec = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			dec[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
		}
		return dec;
	}
}

/*i=0; i<8; i++  
i=1; i<8; i++ 
i=2; i<8; i++ 
i=3; i<8; i++ 
i=4; i<8; i++ 
i=5; i<8; i++ 
i=6; i<8; i++ 
i=7; i<8; i++ 
*/

/*enc[0]= 0%2==0 -- p+1 ==q
enc[1]= 1%2==0 --  a-1 == '
enc[2]= 2%2==0 --  s+1 -- t
enc[3]= 3%2==0 --  s-1 -- r
enc[4]= 4%2==0 --  w+1 -- x
enc[5]= 5%2==0 --  o-1 -- n
enc[6]= 6%2==0 --  r+1 -- s
enc[7]= 7%2==0 --  d-1 -- c
*/

//q`trxnsc