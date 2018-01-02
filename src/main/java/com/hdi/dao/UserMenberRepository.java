package com.hdi.dao;

import com.hdi.model.UserMenbers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 会员信息service
 * @author wanghuidong
 * @date 2017/12/29.
 * @version
 */
public interface UserMenberRepository extends PagingAndSortingRepository<UserMenbers,Integer> {
    /**
     * 查询当前会员号存在不存在
     * @param memberCode
     * @return
     */
    public UserMenbers findByMenberCode(String memberCode);

    /**
     * 查询当前会员号存在不存在
     * @param inviter
     * @return
     */
    public Page findByInviter(String inviter, Pageable pageable);

    /**
     * 查询当前会员号存在不存在
     * @param memberCode
     * @return
     */
    @Query(value = "update UserMenbers as u set u.putPeopleLeft=?1 where u.id=?2",nativeQuery = true)
    @Modifying
    public Integer updateUserMenber(String memberCode,Integer id);

    /**
     * 登录信息
     * @param menberCode
     * @param password
     * @return
     */
    public UserMenbers findByMenberCodeAndPassword(String menberCode, String password);
}
