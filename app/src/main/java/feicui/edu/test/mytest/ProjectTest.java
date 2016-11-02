package feicui.edu.test.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ProjectTest extends Activity {
    @BindView(R.id.pj_btn)
    Button pjBtn;
    HttpURLConnection connection=null;
    InputStream in=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pj_test);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.pj_btn)
    public void onContentChanged() {
        StringBuffer buffer=new StringBuffer();



        try {
            URL url=new URL("http://www.toutiao.com/");//获取对象
            connection= (HttpURLConnection) url.openConnection();//拿到方法
            //获得HttpURLConnection对象
            //设置一些属性
            connection.setReadTimeout(5000);//链接超时时间
            connection.setConnectTimeout(5000);//链接时间
            //网络请求结果码
            int code=connection.getResponseCode();

            if(code==connection.HTTP_OK){
                //读数据
                in=connection.getInputStream();
                byte[] bytes=new byte[1024];
                int len;
                while ((len=in.read(bytes))!=-1){
                    new String(bytes,0,len);
                    buffer.append(new String(bytes,0,len));
                    Log.e("aac", "onContentChanged: buffer"+buffer.toString() );


                }



            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }

    }
    }
}
