package cn.wangxing.qing.controller.common;

import cn.wangxing.qing.pojo.other.SwapData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class FileUploadController {

    private static final String FILE_STORAGE_ROOT = "E:\\workspace\\fileService";
    private static final String BRAND_ROOT = FILE_STORAGE_ROOT+"\\brand\\";

    @RequestMapping("/api/v1/upload/brandImg")
    public SwapData<String> brandImage(@RequestParam("image_file") MultipartFile file){
        try {

            String finalFileName = System.nanoTime()+file.getOriginalFilename();

            File save_file = new File(BRAND_ROOT+finalFileName);
            file.transferTo(save_file);

            return new SwapData<String>("0","文件上传成功","/api/v1/getfile/brand/"+finalFileName+"/");
        } catch (IOException e) {
            return new SwapData<String>("500", "文件存储失败");
        }


    }


    @RequestMapping("/api/v1/getfile/{pre}/{file_name}/")
    public void brandImage(@PathVariable String pre, @PathVariable String file_name, HttpServletResponse response){

        File file = new File(FILE_STORAGE_ROOT + "//" + pre + "//" + file_name);

        try(
            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = new FileInputStream(file)
        ){

            byte[] buff = new byte[1024*100];
            for (int len ; -1 != (len = inputStream.read(buff)) ;){
                outputStream.write(buff,0,len);
            }

        }catch (Exception e){
            System.err.println("[] 文件解析异常");
        }

    }
}
