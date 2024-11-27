package file_getter;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class File {
	private FileWriter fw;
	private BufferedWriter bw;
	
	public File() {
		try {
			fw = new FileWriter("fw.csv");
			bw = new BufferedWriter(fw);
		} catch(Exception e) {
			System.out.println("Lỗi khởi tạo file");
		}
	}

	public FileWriter getFw() {
		return this.fw;
	}

	public void setFw(FileWriter fw) {
		this.fw = fw;
	}

	public BufferedWriter getBw() {
		return this.bw;
	}

	public void setBw(BufferedWriter bw) {
		this.bw = bw;
	}
	
	
}
