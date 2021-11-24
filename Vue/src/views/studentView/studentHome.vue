<template>

  <div class="block"  >
    <el-carousel height="151px" >
      <el-carousel-item > <img src="../../assets/photo/zzu.jpg" ></el-carousel-item>
      <el-carousel-item> <img src="../../assets/photo/zhengzhou.png"></el-carousel-item>
      <el-carousel-item> <img src="../../assets/photo/2.jpg"></el-carousel-item>
    </el-carousel>

    <div style="display: flex;">

      <div style="width: 450px;margin-top: 10px; margin-left: 10px">
        通知公告
        <div class="block" style="width: 300px;margin-top: 10px">
          <el-timeline>
            <el-timeline-item timestamp="2021/9/20" placement="top">
              <el-card>
                <a href="http://www5.zzu.edu.cn/jwc/info/1125/3204.htm" target="_blank" class="astyle">关于2021年下半年全国大学英语四、六级考试报名的通知</a>
              </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2021/9/10" placement="top">
              <el-card>
                <a href="http://www5.zzu.edu.cn/jwc/info/1125/3187.htm" target="_blank" class="astyle">关于推荐2022届优秀应届本科毕业生免试攻读硕士学位研究生工作的通知</a>
              </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2021/7/28" placement="top">
              <el-card>
                <a href="http://www5.zzu.edu.cn/jwc/info/1125/3149.htm" target="_blank" class="astyle">嵩山实验班拟录取学生名单公示</a>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <div style="flex: 1;margin-top: 20px" >
        欢迎您来到教务系统（学生端）

        <p>&#127752 请留意左侧的通知公告</p>
        <br/>
        <br/>
        <br/>
        <br/>
        <p>&#129308祝学业顺利 &#128640</p>

        <dib style="  position: fixed;  bottom: 10px; right: 25%">
          <dib>
            Copyright © 2021 JingeDing. All Rights Reserved.
          </dib>
        </dib>
      </div>

      <div style="width: 200px;margin-top: 20px">
        常用连接：
        <table>
          <tr>
            <td><a href="http://www.zzu.edu.cn/"  target="_blank" class="astyle">郑州大学官网</a></td>
          </tr>
          <tr>
            <td><a href="http://www5.zzu.edu.cn/jwc/xiaoli/xxxl21-22.htm"  target="_blank" class="astyle">查看校历</a></td>
          </tr>
          <tr>
            <td><a href="http://www5.zzu.edu.cn/jwc/index.htm"  target="_blank" class="astyle">教务处</a></td>
          </tr>
          <tr>
            <td><a href="https://doorgx.v.zzu.edu.cn/web/guest/index"  target="_blank" class="astyle">校内门户网站</a></td>
          </tr>
          <tr>
            <td><a href="http://zzulib.portal.chaoxing.com/"  target="_blank" class="astyle">郑州大学图书馆</a></td>
          </tr>
          <tr>
            <td><a href="https://mail.v.zzu.edu.cn/"  target="_blank" class="astyle">郑州大学校园邮箱</a></td>
          </tr>
          <tr>
            <td><a href="https://www.icourse163.org/spoc/university/ZZU#/c"  target="_blank" class="astyle">MOOC学校云</a></td>
          </tr>
          <tr>
            <td><a href="https://www.cnki.net/"  target="_blank" class="astyle">中国知网</a></td>
          </tr>
          <tr>
            <td><a href="https://www.google.com/"  target="_blank" class="astyle">Google</a></td>
          </tr>
          <tr>
            <td><a href="https://www.baidu.com/"  target="_blank" class="astyle">百度搜索</a></td>
          </tr>
          <tr>
            <td><a href="http://www.moe.gov.cn/"  target="_blank" class="astyle">中华人民共和国教育部</a></td>
          </tr>
          <tr>
            <td><a href="https://yz.chsi.com.cn/"  target="_blank" class="astyle">中国研究生招生信息网</a></td>
          </tr>
          <tr>
            <td><a href="https://www.chsi.com.cn/"  target="_blank" class="astyle">中国高等教育学生信息网</a></td>
          </tr>
        </table>
      </div>
    </div>



  </div>


</template>

<script>
export default {
  name: "studentHome",
  data() {
    return {

    }
  },
  created() {
    this.username=sessionStorage.getItem("username")

  }
}
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.astyle{
 text-decoration:none;
}
</style>