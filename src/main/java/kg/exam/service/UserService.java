package kg.exam.service;



import kg.exam.dao.UserDao;
import kg.exam.model.Requests;
import kg.exam.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/add_user")
public class UserService {

    UserDao userDao = new UserDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String create(User user) throws Exception {
        userDao.createUser(user);
        userDao.addRequest(user.getId());
        return user.alert(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Requests> getAllReq_Json(){
        return userDao.getAllReq();
    }

    @DELETE
    @Path("/{reqId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteReq(@PathParam("reqId") Integer reqId) {
        return userDao.deleteReqById(reqId).toString();
    }
}
