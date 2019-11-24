package org.assignment.melongation.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.assignment.melongation.pojo.Answer;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;

import java.util.List;

/**
 * paper服务
 */
public interface PaperService {

    /**
     *
     * @param paper
     */
    void addPaper(Paper paper);

    /**
     *
     * @param i
     * @return
     */
    PageInfo<Paper> findAllPaper(int i);

    /**
     *
     * @param currentPage
     * @param username
     * @return
     */
    PageInfo<Paper> findUserPaper(int currentPage, String username);

    /**
     *
     * @param id
     * @return
     */
    Paper findPaperById(int id);

    /**
     *
     * @param id
     */
    void checkPaper(int id);

    /**
     *
     * @param paperId
     * @return
     */
    Paper getCheckedPaper(Integer paperId);

    /**
     *
     * @param pageNo
     * @param id
     * @return
     */
    PageInfo<Paper> findAllPaperByUser(int pageNo, int id);

}
