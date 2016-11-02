package feicui.edu.test.mytest;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    HttpURLConnection connection=null;


    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onContentChanged();


    }

    /**
     * Android中网络编程
     * HttpConncction
     *             googol进行封装的
     * HttpUrlConncction
     *         //Java源生支持的   好扩展
     *         推荐使用HttpUrlConncction  好扩展，响应速度快
     *         URL
     *            统一资源标识符、
     *             用来封装链接
     *             协议：
     *              比如：http
     *            主机名：
     *                127.0.0.1   localhost
     *            端口号：
     *                8080
     *             用户信息：
     *                   比如:用户名 密码
     *     HttpUrlConncction使用
     *          1.获取HttpUrlConncction对象
     *           URL url=new URL("链接地址");
     *          2
     */
    @Override
    public void onContentChanged() {
        InputStream in = null;
        try {
            URL url=new URL("https://www.baidu.com/");
            connection= (HttpURLConnection) url.openConnection();
            //配置一些信息
//              connection.setDoOutput();可以服务端传递数据
//            connection.setDoInput(true);//可以读取服务端返回的数据
            connection.setReadTimeout(3000);//设置超时时间
            connection.setConnectTimeout(3000);//设置链接时间
            //网络请求结果码
            //404：一般情况下是客户端的问题
            //500:  一般情况下是服务端的问题
            //200:网络请求成功
            int code=connection.getResponseCode();
            Log.e("aac", "onContentChanged: code=="+code );
            if(code==connection.HTTP_OK){//判断网络链接
                //读数据
                in=connection.getInputStream();
                byte[] bytes=new  byte[1024];
                int len;
                StringBuffer buffer=new StringBuffer();
                while ((len=in.read(bytes))!=-1){

                    buffer.append(new String(bytes,0,len));
                }
                Log.e("aac", "onContentChanged: buffer"+buffer.toString() );

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //网络链接最后需要断开链接
            if (connection!=null){
                connection.disconnect();

            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
