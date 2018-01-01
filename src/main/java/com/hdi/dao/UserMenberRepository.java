package com.hdi.dao;

import com.hdi.model.UserMembers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 会员信息service
 * @author 王慧东
 * @date 2017/12/29.
 * @version
 */
public interface UserMenberRepository extends PagingAndSortingRepository<UserMembers,Integer> {
    /**
     * 查询当前会员号存在不存在
     * @param memberCode
     * @return
     */
    public UserMembers findByMemberCode(String memberCode);


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
    @Query(value = "update UserMembers as u set u.putPeopleLeft=?1 where u.id=?2",nativeQuery = true)
    @Modifying
    public Integer updateUserMenber(String memberCode,Integer id);
}
