package org.smart4j.framework.core.impl.support;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.util.ClassUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author tf
 * @create 2017-06-27 18:46
 **/
public abstract class ClassTemplate {
    private Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    public ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> enumerationUrl = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            while (enumerationUrl.hasMoreElements()) {
                URL url = enumerationUrl.nextElement();
                if (url != null) {
                    // 获取协议名（分为 file 与 jar）
                    String protocol = url.getProtocol();
                    if ("file".equalsIgnoreCase(protocol)) {
                        //在java中获取文件路径的时候，有时候会获取到空格，但是在中文编码环境下，空格会变成“%20”从而使得路径错误
//                        String packagePath = url.getPath().replaceAll("%20", " ");
                        String packagePath = URLDecoder.decode(url.getPath(), FrameworkConstant.ENCODING);
                        addClass(classList, packageName, packagePath);
                    } else {
                        // 若在 jar 包中，则解析 jar 包中的 entry
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            // 判断该 entry 是否为 class
                            if (jarEntryName.endsWith(".class")) {
                                // 获取类名
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                // 执行添加类操作
                                doAddClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("查找指定包名下类文件失败", e);
            throw new RuntimeException(e);
        }
        return classList;
    }

    private void addClass(List<Class<?>> classList, String packageName, String packagePath) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files) {
            //判断是否为文件或目录
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                //执行添加类操作
                doAddClass(classList, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + File.separator + subPackagePath;
                }

                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                // 递归调用
                addClass(classList, subPackageName, subPackagePath);
            }
        }
    }

    private void doAddClass(List<Class<?>> classList, String className) {
        //加载类
        Class<?> clzz = ClassUtil.loadClass(className, false);
        //判断是否添加类
        if (checkAddClass(clzz)) {
            classList.add(clzz);
        }
    }

    protected abstract boolean checkAddClass(Class<?> clz) ;

    public static void main(String[] args) {
        URL url = ClassUtil.getClassLoader().getResource("org/smart4j/framework");
        String urlPath = url.getPath();
        System.out.println(urlPath);
//        File[] files = new File(urlPath).listFiles();
//        for (File f : files) {
//            System.out.println(f.getName());
//        }
    }
}
