package com.achelous.configuration;


import com.achelous.Enum.StatementType;
import com.achelous.annotation.Insert;
import com.achelous.annotation.Mapper;
import com.achelous.annotation.Param;
import com.achelous.annotation.Select;
import com.achelous.mapper.MethodMapper;
import com.sun.xml.internal.ws.api.model.ExceptionType;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @Auther: fanJiang
 * @Date: Create in 20:49 2018/4/2
 */
public class MapperRegistry {

    private Map<Class, Map<String,MethodMapper>> container = new HashMap<>();
    private List<String> classNames = new ArrayList<>();

    public MapperRegistry() {
        initContainer();
    }

    private void initContainer() {
        String path = "E:\\workspace\\mybatis01\\src\\main\\java\\com\\achelous\\dao";
        scanPackage(path);
        doInitContainer();
    }

    private void scanPackage(String path){
        // TODO  the path can configuration
        File dir = new File(path);
        for (File file: dir.listFiles()) {
            if (file.isDirectory()) {
                scanPackage(path + "\\" + file.getName());
            } else {
                String className = path.substring(path.indexOf("com")) + "\\" + file.getName().replace(".java", "");
                className = className.replace("\\", ".");
                classNames.add(className.trim());
            }
        }
    }

    private void doInitContainer(){
        if (classNames.isEmpty()) {return ;}
        for (String name: classNames) {
            try {
                Class<?> clazz = Class.forName(name);
                if (clazz.isAnnotationPresent(Mapper.class)) {
//                    String beanName = firstLower(clazz.getSimpleName());

                    container.put(clazz, getMethodMapper(clazz));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, MethodMapper> getMethodMapper(Class clazz) {
        Map<String, MethodMapper> methodMapper = new HashMap<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Select.class)) {
                Select select = method.getAnnotation(Select.class);
                Class<?> returnType = method.getReturnType();
                String sql = select.value();
                Map<Integer, String> paramMap = getParamMap(method.getParameters());
                methodMapper.put(method.getName(), new MethodMapper(returnType, sql, paramMap, StatementType.SELECT));
            }
            if (method.isAnnotationPresent(Insert.class)) {
                Insert insert = method.getAnnotation(Insert.class);
                String sql = insert.value();
                Map<Integer, String> paramMap = getParamMap(method.getParameters());
                methodMapper.put(method.getName(), new MethodMapper(int.class, sql, paramMap, StatementType.INSERT));
            }
            // TODO   update;
        }
        return methodMapper;
    }


    private Map<Integer, String> getParamMap(Parameter[] parameters) {
        Map<Integer, String> paramMap = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            String name;
            if (parameters[i].isAnnotationPresent(Param.class)){
                name = parameters[i].getAnnotation(Param.class).value();
            } else {
                name = String.valueOf(i);
            }
            paramMap.put(i, name);
        }
        return paramMap;
    }

    private String firstLower(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public <T> T getMapper(Class<?> clazz) {
        return (T) container.get(clazz);
    }
}
