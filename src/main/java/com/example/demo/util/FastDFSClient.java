package com.example.demo.util;

import com.example.demo.model.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FastDFS工具类
 */
public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private static StorageClient storageClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    /**
     * 类加载时配置实例化
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            TrackerClient trackerClient = new TrackerClient();
            //获取追踪服务器
            trackerServer = trackerClient.getTrackerServer();
            //获取客户端
            storageClient = new StorageClient(trackerServer, null);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }

    /**
     * 获取跟踪服务器 TrackerServer  该方法未使用
     * @return
     * @throws IOException
     */
//    private static TrackerServer getTrackerServer() throws IOException {
//        TrackerClient trackerClient = new TrackerClient();
//        TrackerServer trackerServer = trackerClient.getTrackerServer();
//        return trackerServer;
//    }

    /**
     * 获取客户端 StorageClient  该方法未使用
     * @return
     * @throws IOException
     */
//    private static StorageClient getStorageClient() throws IOException {
//        TrackerServer trackerServer = getTrackerServer();
//        StorageClient storageClient = new StorageClient(trackerServer, null);
//        return storageClient;
//    }

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "File Length:" + file.getContent(
        ).length);
        //⽂件属性信息
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            //上传
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt()
                    , meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(),
                    e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime
        ) + " ms");
        //验证上传结果
        if (uploadResults == null && storageClient!=null) {
            logger.error("upload file fail, error code:" + storageClient.getErrorCode(
            ));
        }
        //上传⽂件成功会返回 groupName。
        logger.info("upload file successfully!!!" + "group_name:" + uploadResults[0] +
                ", remoteFileName:" + " " + uploadResults[1]);
        return uploadResults;
    }

    /**
     *获取文件
     * @param groupName 文件名
     * @param remoteFileName 文件组
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
             storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }


    /**
     * 下载文件
     * @param groupName 文件名
     * @param remoteFileName 文件组
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName 文件名
     * @param remoteFileName 文件组
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }


}
