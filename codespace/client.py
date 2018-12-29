from tornado.web import Application
import tornado.ioloop
import suds
from  suds  import  client
from aad.phone import *
from aad.cacheutils import CacheServe
import json

class Index(tornado.web.RequestHandler):
    def get(self):
        print("接受到用户的get请求 ")
        self.render("login.html", failmsg=None)
class Usehandler(tornado.web.RequestHandler):
    def post(self):

        print("接受到用户的user_*请求")
        method=self.get_argument("action")
        print("method-->", method)
        if method=="login":
            username=self.get_argument("usename")
            password=self.get_argument("password")
            print(username, password)
            url="http://127.0.0.1:8200/userdataservice/user?wsdl"
            service=suds.client.Client(url)
            msg=service.service.checkUserLogin(username,password)
            print("msg-->", msg)
            headsInfo = self.request.headers

            # print("headsInfo-->",headsInfo)
            hinfo = headsInfo["User-Agent"]
            print("请求的头的信息为:", hinfo)
            val = checkPCorMobile(hinfo)
            print("val",val)
            jsonDatas = ""
            if  msg=='登录成功':
             cacheService = CacheServe()
             jsonDatas = cacheService.getdate("tmenudata")

             if val=="PC":
                self.render("index.html",contentdata=jsonDatas)
             else:
                       #json数据格式
                self.finish({"msg":"success","contentdata":jsonDatas})
            else:
              if val == "PC":
                self.render("login.html",failmsg=msg)
              else:
                self.finish({"msg": "fail"})
        elif method=="register":
         self.render("register.html")
class antvhandler(tornado.web.RequestHandler):
    def post(self, *args, **kwargs):
        print("接受到antv请求")
        method = self.get_argument("datas")
        if method == "classestostucount":
            url = "http://127.0.0.1:8200/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.getantv1()
            print("data-->", data)
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})


settings={

   "template_path": "templates",
    "static_path": "static",

}
app=tornado.web.Application([(r"/",Index),(r'/user',Usehandler),(r'/antv',antvhandler)],**settings)
if __name__=="__main__":
    app.listen(8100)
    tornado.ioloop.IOLoop.current().start()