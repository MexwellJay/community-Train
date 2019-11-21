package study.mexwelljay.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.mexwelljay.community.dto.AccessTokenDTO;
import study.mexwelljay.community.dto.GithubUser;
import study.mexwelljay.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther Jay
 * @date 2019/11/6
 * 模块概述：
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.url}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);//通过accessToken得到了用户所有信息
//        System.out.println(user.getName());
        if (user != null) {
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            //登录失败，重新登陆
            return "redirect:/";
        }
    }
}
