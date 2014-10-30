package com.src.myant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class WriteLog extends Task {

    String apknmae;// apk名称
	String projectpath;// 项目路径
    
	public String getProjectpath() {
		return projectpath;
	}

	public void setProjectpath(String projectpath) {
		this.projectpath = projectpath;
	}

	public String getApknmae() {
		return apknmae;
	}

	public void setApknmae(String apknmae) {
		this.apknmae = apknmae;
	}

	@Override
	public void execute() throws BuildException {
		// TODO Auto-generated method stub
		super.execute();
		String apkFilePath = projectpath + "/release/log.txt";
		File file = new File(apkFilePath);
		if (file.exists()) {
			System.out.println("文件已存在");
		} else {
			if (!file.getParentFile().exists()) {
				if (!file.getParentFile().mkdir())
					System.out.println("文件目录创建失败");
			}
			try {
				if (file.createNewFile()) {
					System.out.println("创建文件成功!");
				} else {
					System.out.println("创建文件失败!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("文件创建完成或者已经存在！");

		// Scanner scanner = new Scanner(System.in);
		String name = apknmae;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		StringBuilder getcontent = new StringBuilder();
		try {
			System.out.println("开始读取log......");
			bufferedReader = new BufferedReader(new FileReader(file));
			String data = "";
			while ((data = bufferedReader.readLine()) != null) {
				getcontent.append(data);
				System.out.println("data");
			}
			String resdata = getcontent.toString();
			System.out.println(resdata);
			System.out.println("Log 读取完成.....");
			String[] checks = resdata.split("#");
			System.out.println("name=  " + name);
			if (checks[checks.length - 1].equals(name)) {
				System.out.println("文件重复！");
				return;
			} else {
				if (!resdata.equals(""))
					resdata += "#";
				resdata += name;
				// bufferedReader.close();
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(resdata);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (bufferedWriter != null)
					bufferedWriter.close();
				bufferedReader = null;
				bufferedWriter = null;
				file = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
