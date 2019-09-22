package cn.bus.tool;

import java.sql.*;

public class PageUtil {

	public static int totalPage(int total)//计算总页数
	{
		int totalPage=0;
		if (total%3==0) {
			totalPage=total/3;
		}else {
			totalPage=total/3+1;
		}
		if (totalPage==0) {
			totalPage=1;
		}
		return totalPage;
	}
}
