package DemoXunit;

/**
 * Created by duzhe on 2018/12/18.
 *
 * @Description:
 */
public class Shopping {

    Login login = new Login();
    Products pro;

    /**
     * 通过ID查找商品价格
     * @param proId 商品ID
     * @return 商品价格 ； -1 没有此商品；-2 未登录
     */
    public int getPrice(int proId){
        if(login.isLogin == true){
            if (proId <= 0 ){ //1
                return -1;
            }else{
                Products p= Products.getPro(proId);

                return p.getPrice();
            }
        }else{

            return -2;
        }
    }


    /**
     * 判断库存数量，购买商品
     * @param proId
     * @param count 购买数量
     * @return 0 库存不足； 1 购买成功；-1 未登录；
     */
    public int buy(int proId, int count){
        if(login.isLogin == true){
            pro = getPro(proId);
            if(pro != null){
                int proCount = pro.getCount();

                if(proCount > count ){  //
                    pro.setCount(proCount - count );
                    return 1;
                }else{
                    return 0;
                }
            }else{
                return -2;
            }
        }else{
            return -1;
        }
    }

    /**
     * 通过ID获取商品信息
     * @param proId
     * @return 商品信息
     */
    public Products getPro(int proId){


            if (proId <= 0) {

                return null;
            } else {
                return Products.getPro(proId);
            }

    }


    /**
     * buy的正确打开方式
     * @param proId
     * @param count
     * @return  -3 商品不存在；-2 未登录；-1 购买数量<=0；1 购买成功; 0 库存不足
     */
    public int buys(int proId, int count) {
        pro = getPro(proId);
        if (login.isLogin == false) {
            return -2;
        } else if(pro == null){ //判断商品是否存在
            return -3;
        }else{
                //欲购买数量是否大于0
                if (count <= 0) {
                    return -1;
                }

            int proCount = pro.getCount();

            //库存充盈，可以购买
            if (proCount >= count) {
                //购买成功，减少对应库存数，且库存最低为0
                if ((proCount - count) >= 0) {
                    pro.setCount(proCount - count);
                } else {
                    pro.setCount(0);
                }
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args){
        Shopping shopping = new Shopping();
        Login login = new Login();
        login.userLogin("admin","123456");
//        System.out.println(shopping.buy(5,5));
        System.out.println(shopping.buys(2,1));
    }

}
