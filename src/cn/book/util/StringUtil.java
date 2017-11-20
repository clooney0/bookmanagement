package cn.book.util;

/**
 * 字符串工具类
 * @author Administrator
 *
 */

public class StringUtil {

	//一般工具类中方法都是静态方法  构造器不提供   类名.方法名
	
	private StringUtil(){
		
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str){
		return str!=null && !"".equals(str.trim());
	}
	

}
