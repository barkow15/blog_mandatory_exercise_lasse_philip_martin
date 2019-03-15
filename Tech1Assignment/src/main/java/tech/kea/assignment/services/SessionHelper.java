package tech.kea.assignment.services;

import org.springframework.stereotype.Service;
import tech.kea.assignment.model.User;

import javax.servlet.http.HttpSession;

@Service
public class SessionHelper
{
    public boolean isAdmin(HttpSession session)
    {
        try
        {
            return ((Boolean)session.getAttribute("isAdmin")).booleanValue();
        }catch(Exception e)
        {
            return false;
        }
    }

    public User getUser(HttpSession session)
    {
        try
        {
            return ((User)session.getAttribute("user"));
        }catch(Exception e)
        {
            return null;
        }
    }
}
