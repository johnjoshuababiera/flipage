package com.cpu.comment;


import com.cpu.comments.Comment;
import com.cpu.news.NewsService;
import com.cpu.post.PostService;
import com.cpu.topic.TopicService;
import com.cpu.user.User;
import com.cpu.utils.DatabaseDto;
import com.cpu.utils.SignInUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/page/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PostService postService;

    @RequestMapping("/news")
    public String newsList(Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }

        model.addAttribute("newsList", newsService.findAll());
        return "/comment/news_list.html";
    }

    @RequestMapping("/news/view")
    public String viewNewsComments(@RequestParam long id, Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }

        try {
            model.addAttribute("news", newsService.findOne(id));
            model.addAttribute("comments", service.findByNewsId(id));
        } catch (Exception e) {
            redir.addFlashAttribute("error", e.getMessage());
            return "redirect:/page/comment/news";
        }
        return "/comment/news_comments.html";
    }


    @RequestMapping("/news/delete")
    public String newsList(@RequestParam Long id, @RequestParam Long newsId, Model model, RedirectAttributes redir) {
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        service.removeById(id);
        return "redirect:/page/comment/news/view?id="+newsId;
    }


    @RequestMapping("/forums")
    public String forumsList(Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }
        model.addAttribute("posts", postService.findAll());
        return "/comment/forums_list.html";
    }

    @RequestMapping("/forums/view")
    public String viewForumComments(@RequestParam long id, Model model, RedirectAttributes redir) {
        model.addAttribute("database", new DatabaseDto());
        if (SignInUtils.getInstance().getCurrentUser() == null) {
            return "redirect:/";
        }

        model.addAttribute("comments", service.findByPostId(id));
        return "/comment/news_list.html";
    }


}
