package com.example.demo.service.common;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenpeng on 2017/8/1 0001.
 * redis服务
 */

@Service
public class RedisService {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /*
     * HashMap操作
     */
    @Resource
    private HashOperations<String, String, Object> hashOperations;

    /*
     * Object操作
     */
    @Resource
    private ValueOperations<String, Object> valueOperations;

    /*
     *  List操作
     */
    @Resource
    private ListOperations<String, Object> listOperations;

    /*
     *  set操作
     */
    @Resource
    private SetOperations<String, Object> setOperations;

    /*
     * ZSet 操作
     */
    @Resource
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 设置过期时间
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    private void setExpire (String key,long expire){
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    //---------------------------------------------------------------------
    // HashOperations -> Redis Redis Hash 操作
    //---------------------------------------------------------------------

    /**
     * 向redis 中添加内容
     *
     * @param key       保存key
     * @param hashKey   hashKey
     * @param data      保存对象 data
     * @param expire    过期时间    -1：表示不过期
     *
     * @author chenming
     * @date 2017-06-12
     */
    public void addHashValue(String key,String hashKey, Object data, long expire) {
        hashOperations.put(key, hashKey, data);

        setExpire(key,expire);
    }

    /**
     * Hash 添加数据
     *
     * @param key   key
     * @param map   data
     *
     * @author chenming
     * @date 2017-06-12
     */
    public void addAllHashValue(String key, Map<String, Object> map, long expire) {
        hashOperations.putAll(key, map);

        setExpire(key,expire);
    }

    /**
     * 删除hash key
     *
     * @param key       key
     * @param hashKey   hashKey
     *
     * @author chenming
     * @date 2017-06-12
     *
     */
    public long deleteHashValue(String key, String hashKey) {
        return hashOperations.delete(key, hashKey);
    }

    /**
     * 获取单个数据
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public Object getHashValue(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

    /**
     * 批量获取数据
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public List<Object> getHashAllValue(String key) {
        return hashOperations.values(key);
    }

    /**
     * 批量获取指定hashKey的数据
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public List<Object> getHashMultiValue(String key, List<String> hashKeys) {
        return  hashOperations.multiGet(key, hashKeys);
    }

    /**
     * 获取hash数量
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public Long getHashCount(String key) {
        return hashOperations.size(key);
    }

    /**
     * 判断指定hash的hashKey是否存在
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashKeyExist(String key, String hashKey){
        return hashOperations.hasKey(key, hashKey);
    }


    //---------------------------------------------------------------------
    // ValueOperations -> Redis String/Value 操作
    //---------------------------------------------------------------------

    /**
     * 设置key-value值
     *
     * @author chenming
     * @date 2017-06-13
     */
    public void addValue(String key, Object value,long expire){
        valueOperations.set(key, value);

        setExpire(key,expire);
    }

    /**
     * 设置key-value值,传入时间单位
     *
     * @author chenming
     * @date 2017-06-13
     */
    public void addValue(String key, Object value,long expire, TimeUnit timeUnit){
        valueOperations.set(key, value, expire, timeUnit);

    }

    /**
     * 设置key-value值, 无过期时间
     *
     * @author chenming
     * @date 2017-06-13
     */
    public void addValue(String key, Object value){
        valueOperations.set(key, value);
    }

    /**
     * 获取key的值
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public Object getValue(String key){
        return valueOperations.get(key);
    }

    //---------------------------------------------------------------------
    // redisTemplate
    //---------------------------------------------------------------------

    /**
     * 判断指定key是否存在
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据指定key删除
     *
     * @param key
     *
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 判断指定key的hashKey是否存在
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 设置超时
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public void expire(String key, final long timeout, final TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     *
     */
    public long ttl(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取所有key
     *
     * @author chenming
     * @date 2017-06-13
     *
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除key
     *
     * @author chenming
     * @date 2017-06-13
     */
    public void delete(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    //---------------------------------------------------------------------
    // ZSetOperations -> Redis Sort Set 操作
    //---------------------------------------------------------------------

    /**
     * 设置zset值
     *
     * @author chenming
     * @date 2017-06-12
     *
     */
    public boolean addZSetValue(String key, Object member, long score){
        return zSetOperations.add(key, member, score);
    }

    /**
     * 设置zset值
     *
     * @author chenming
     * @date 2017-06-12
     */
    public boolean addZSetValue(String key, Object member, double score){
        return zSetOperations.add(key, member, score);
    }

    /**
     * 批量设置zset值
     *
     * @author chenming
     * @date 2017-06-12
     */
    public long addBatchZSetValue(String key, Set<ZSetOperations.TypedTuple<Object>> tuples){
        return zSetOperations.add(key, tuples);
    }

    /**
     * 自增zset值
     *
     * @author chenming
     * @date 2017-06-12
     *
     */
    public void incZSetValue(String key, String member, long delta){
        zSetOperations.incrementScore(key, member, delta);
    }

    /**
     * 获取zset数量
     *
     * @author chenming
     * @date 2017-06-12
     *
     */
    public long getZSetScore(String key, String member){
        Double score = zSetOperations.score(key, member);
        if(score==null){
            return 0;
        }else{
            return score.longValue();
        }
    }

    /**
     * 获取有序集 key 中成员 member 的排名 。其中有序集成员按 score 值递减 (从小到大) 排序。
     *
     * @author chenming
     * @date 2017-06-13
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRank(String key, long start, long end){
        return zSetOperations.rangeWithScores(key, start, end);
    }


    //---------------------------------------------------------------------
    // listOperations -> Redis List() 操作
    //---------------------------------------------------------------------

    /**
     * 添加list列表
     *
     * @author chenming
     * @date 2017-06-16
     */
    public void addListValue(String key,Object list){
        listOperations.leftPush(key,list);
    }

    /**
     * 获取指定Key对应的list
     *
     * @author chenming
     * @date 2017-06-16
     */
    public Object getListValue(String key){
        return listOperations.leftPop(key);
    }

    //---------------------------------------------------------------------
    // setOperations -> Redis Set() 操作
    //---------------------------------------------------------------------
    /**
     * 添加Set集合集合
     *
     * @author chenming
     * @date 2017-06-16
     */
    public void addSetValue(String key,Object list){
        setOperations.add(key,list);
    }

    /**
     * 获取指定Key对应的set
     *
     * @author chenming
     * @date 2017-06-16
     */
    public Object getSetValue(String key){
        return setOperations.members(key);
    }

    /**
     * 获取并移除指定key的值
     *
     * @author chenming
     * @date 2017-06-17
     */
    public Object popSetValue(String key){
        return setOperations.pop(key);
    }

}
