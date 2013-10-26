import java.math.*;
import java.lang.*;
import java.io.*;

public class RSA {
	private BigInteger	n;
	private BigInteger	e;
	private StringBuffer	binarystr = new StringBuffer();
	private BigInteger	intmsg;
	
	public RSA (String sn, String se) {
		n = new BigInteger(sn);
		e = new BigInteger(se);
	}
	public StringBuffer convertToAscii(String astr) {
		
		StringBuffer binarystr = new StringBuffer(); 
		for (int j = 0; j < astr.length(); j++) {
			Character achar = new Character(astr.charAt(j));
			String a = achar.toString();
			byte[] plainint = a.getBytes();
			for (int i = 0; i < plainint.length; i++) { //8; i++) {
				String binstr =Integer.toBinaryString(plainint[i]);
				for (int k = 8; k > binstr.length(); k--)
					binarystr.append("0");
				binarystr.append(binstr);
			}
		}
		
		return binarystr;
	}
	
	public void Calculate(String msg) {
		StringBuffer binarystr = convertToAscii(msg);
		System.out.println(binarystr);
		
		String abuf = binarystr.toString();
		char[] outc = abuf.toCharArray();
		char	achar;
		int		m = 0;
		intmsg = new BigInteger("0");	
		for (int i = outc.length; i > 0; i--) {
			achar = outc[i - 1];
			if (achar == '1') {
				BigInteger aint = (new BigInteger("2")).pow(m);
				intmsg = intmsg.add(aint);
				}
			m++;
		}
		System.out.println(intmsg);
	
		BigInteger finpart = intmsg.modPow(e, n);
		String finstr = finpart.toString();
		System.out.println("cypertext:\n" + finstr);
	}
	
	public static void main (String[] args) {
		RSA aras = new RSA(new String("46947848749720430529628739081"), new String("37267486263679235062064536973"));
		aras.Calculate(new String("Give me an A"));
	}
}
