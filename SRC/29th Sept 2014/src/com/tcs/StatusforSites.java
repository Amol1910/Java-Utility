package com.tcs;

public class StatusforSites {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		StatusHelper status=new StatusHelper();
		
		System.out.println ("Please wait .....Data Process In Progress");
		status.writeandSendEmail();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
//if( $code == 'SR' ) $country = 'Suriname';