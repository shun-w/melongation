package org.assignment.melongation.mapper;


//import net.bytebuddy.asm.Advice;
import org.assignment.melongation.mapper.PaperMapper;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
public class PaperMapperTest {
    @Autowired
    private PaperMapper paperMapper;

    @Test
    public void testSelectPaperById() {
        Paper paper = paperMapper.selectPaperById(1);
        System.out.println(paper.toString());
    }

    @Test
    public void testSelectAllPaper() {
        List<Paper> papers = paperMapper.selectAllPaper();
        System.out.println(papers.size());
        for (Paper paper : papers) {
            System.out.println(paper.toString());
        }
    }

    @Test
    public void testSavePaper() {
        Paper paper = new Paper(null, 1, "shun1", "",
                (new Date()), 1, true);


        Integer id = paperMapper.savePaper(paper);
        testSelectAllPaper();
        System.out.println("id:" + paper.getId());
    }

    @Test
    public void testDeletePaperById() {
        Paper paper = new Paper(null, 1, "cds", "",
                (new Date()), 1, true);


        paperMapper.savePaper(paper);
        paperMapper.deletePaperById(1);
    }

    @Test
    public void testFindAllPaper() {
        List<Paper> allPaper = paperMapper.findAllPaper();
        System.out.println("s");
    }

}
