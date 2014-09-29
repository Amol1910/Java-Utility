import java.io.File;


public class TestChk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file =new File("E://Site Data Status//Site Data Demo.xlsx");
		if(file.exists())
		file.delete();
		String str="144.123";
		System.out.println(str.startsWith("144.1235555"));

	}

}
