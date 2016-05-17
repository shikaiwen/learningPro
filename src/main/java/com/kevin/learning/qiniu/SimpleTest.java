package com.kevin.learning.qiniu;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class SimpleTest {

	  //设置好账号的ACCESS_KEY和SECRET_KEY
	  String ACCESS_KEY = "_a02K1I_jmeZFh70AaZCfGlZKliRqEt9xNFr_c1W";
	  String SECRET_KEY = "d73clYznwLBMxaAGmGsGbFMOZml0jtkz_CVf9kvz";
	  //要上传的空间
	  String bucketname = "kevintest";
	  //上传到七牛后保存的文件名 
//	  String key = "2016-10/pp/" + "timg22.jpg";
	  String key = "弟子规学习心得3_钟茂森.f4v";
	  //上传文件的路径
//	  String FilePath = "C:/Users/凯文/Desktop/timg.jpg";
	  String FilePath = "G:/BaiduYunDownload/弟子规学习心得3_钟茂森.f4v";

	  //密钥配置
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  
	  //创建上传对象
	  UploadManager uploadManager = new UploadManager();

	  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	  public String getUpToken(){
		  String persistentOps = "avthumb/mp3";
		 return auth.uploadToken(bucketname,null,3600,
				  new StringMap().put("persistentOps", persistentOps),
				  true);
//		  return auth.uploadToken(bucketname, key );
//	      return auth.uploadToken(bucketname);
	  }

	  public void upload() throws IOException{
	    try {
	      //调用put方法上传
	    
	      Response res = uploadManager.put(FilePath, key, getUpToken());
	      //uploadManager.put(filePath, SECRET_KEY, token, params, mime, checkCrc)
	      //打印返回的信息
	      System.out.println(res.bodyString()); 
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	          }
	      }       
	  }

	  public static void main(String args[]) throws IOException{  
	    new SimpleTest().upload();
	  }
}
