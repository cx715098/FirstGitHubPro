import java.util.Scanner;

/**
 * @author: 崔鑫
 * @date: 2022/2/17
 * @description: 吃货联盟订餐系统
 */
public class OrderFoodSys {
    public static void main(String[] args) {
        //定义数据主体： 订单 菜品
        String[] disNames = {"红烧带鱼", "鱼香肉丝", "时令蔬菜"};//菜品名称
        double[] price = {38.0, 20.0, 10.0};//单价
        int[] praiseNums = new int[3];//点赞数
        //定义数据主体： 订单-订餐人 餐品信息 送餐时间 送餐地址 总金额 订单状态
        String[] names = new String[4];
        String[] dishMsg = new String[4]; //菜品名称+份数
        int[] times = new int[4];
        String[] address = new String[4];
        double[] sumPrices = new double[4];
        int[] states = new int[4];//0,已预订  1,已完成

        //初始化2个订单
        names[0] = "张三";
        dishMsg[0] = "红烧带鱼 2份";
        times[0] = 10;
        address[0] = "知春路223号";
        sumPrices[0] = 76;//餐费大于50，免配送费，小于50,6元配送费
        states[0] = 0;

        names[1] = "李四";
        dishMsg[1] = "鱼香肉丝 1份";
        times[1] = 10;
        address[1] = "天成路207号";
        sumPrices[1] = 26;//餐费大于50，免配送费，小于50,6元配送费
        states[1] = 1;

        // 搭建项目整体流程框架
        Scanner in = new Scanner(System.in);
        int num = -1; // 用户输入0返回时的输入数字，num==0时重复循环，num在循环中反复修改
        System.out.println("\t欢迎使用“吃货联盟订餐系统”");
        //记录用户是否退出系统的状态： 退出 true 不退出false
        boolean flag = false;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1.我要订餐");
            System.out.println("2.查看餐袋");
            System.out.println("3.签收订单");
            System.out.println("4.删除订单");
            System.out.println("5.我要点赞");
            System.out.println("6.退出系统");
            System.out.println("-------------------------------------");
            System.out.print("请选择：");
            int choose = in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("\n--------我要订餐--------");
                    // 订餐前提是订单未满，boolean变量标识订单是否满
                    boolean isAdd = false;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] == null) {
                            isAdd = true;
                            // 开始订餐
                            System.out.println("请输入订餐人姓名：");
                            String name = in.next();
                            // 循环输出菜品信息
                            System.out.println("序号\t菜名\t单价");
                            for (int j = 0; j < disNames.length; j++) {
                                String praise = (praiseNums[j]==0)?"":praiseNums[j]+"赞";
                                System.out.println((j + 1) + "\t" + disNames[j] + "\t" + price[j] + "\t" + praise);
                            }
                            // 输入菜品编号并进行判断
                            System.out.print("请选择您要点的菜品编号：");
                            int no = in.nextInt();
                            while (no<1 || no>disNames.length){
                                System.out.println("本店没有此菜品，请重新选择：");
                                no = in.nextInt();
                            }
                            // 点菜份数
                            System.out.print("请选择您需要的份数：");
                            int number = in.nextInt();
                            // 输入送餐时间并进行判断
                            System.out.print("请输入送餐时间（送餐时间只能在10-20之间的整点）：");
                            int time = in.nextInt();
                            while (time<10 ||time>20){
                                System.out.println("您输入的送餐时间有误，请输入10-20之间的整点数：");
                                time = in.nextInt();
                            }
                            // 送餐地址
                            System.out.print("请输入送餐地址：");
                            String add = in.next();
                            // 添加到订单信息
                            System.out.println("订餐成功！");
                            // 菜品信息： 菜品名称+份数
                            String dishIofo = disNames[no-1]+" " + number+"份";
                            System.out.println("您订的是："+dishIofo); // **用户选择的菜品是从1开始，下标要-1
                            System.out.println("送餐时间："+time+"点");
                            // 餐费  配送费  总计
                            // 餐费 prices数字下标 用户选的编号-1
                            double dishPrice = price[no-1]*number;
                            double peiSong = dishPrice>50?0:6;
                            double sumPrice = dishPrice +peiSong;
                            System.out.println("餐费："+dishPrice+"\t配送费："+peiSong+"\t总计："+sumPrice);
                            // 把订餐信息添加到订单信息,插入订单位置是关键 i
//                            String[] names = new String[4];
//                            String[] dishMsg = new String[4]; //菜品名称+份数
//                            int[] times = new int[4];
//                            String[] address = new String[4];
//                            double[] sumPrices = new double[4];
//                            int[] states = new int[4];//0,已预订  1,已完成
                            names[i] = name;
                            dishMsg[i] =dishIofo;
                            times[i] = time;
                            address[i]= add;
                            sumPrices[i]= sumPrice;
                            break; //此次订餐完成跳出循环
                        }
                    }
                    if (!isAdd) {
                        System.out.println("对不起，您的餐袋已满！");
                    }
                    break;
                case 2:
                    System.out.println("\n--------查看餐袋--------");
//                    订单-订餐人 餐品信息 送餐时间 送餐地址 总金额 订单状态
                    System.out.println("订单\t订餐人\t餐品信息\t\t\t送餐时间\t送餐地址\t\t总金额\t订单状态");
                    for (int i = 0; i < names.length; i++) {
                        String time = times[i] + "点";
                        String state = (states[i] == 0) ? "已预订" : "已完成";
                        if (names[i] != null)
                            System.out.println((i + 1) + "\t" + names[i] + "\t\t" + dishMsg[i] + "\t\t" + time
                                    + "\t" + address[i] + "\t" + sumPrices[i] + "\t" + state);
                    }
                    break;
                case 3:
                    System.out.println("\n--------签收订单--------");
                    // 签收之前判断订单是否存在，false 不存在  true 存在-已完成 不能签收  存在-已预订 可以牵手
                    boolean isSigh = false;
                    System.out.println("请输入您要签收的订单编号：");
                    int signNo = in.nextInt();
                    for (int i =0;i<names.length;i++){
                        if (names[i]!=null &&states[i]==0 && i ==signNo-1){
                            // 有订单信息，订单状态为已预订，是用户查找的编号
                            // 能签收
                            isSigh = true;
                            states[i] = 1;
                            System.out.println("订单签收成功！");
                        }else if(names[i]!=null &&states[i]==1 && i ==signNo-1) {
                            // 订单状态为已完成，不能签收
                            isSigh = true;
                            System.out.println("你选择的订单已签收！不能再次签收");
                        }
                    }
                    if (!isSigh){
                        System.out.println("您选择的订单不存在！");
                    }
                    break;
                case 4:
                    System.out.println("\n--------删除订单--------");
                    // 删除之前，判断订单是否存在 boolean
                    boolean isDelete = false;// 签收之前判断订单是否存在，false 不存在  true 存在-已完成 不能签收  存在-已预订 可以牵手
                    System.out.println("请输入您要删除的订单编号：");
                    int deleteNo = in.nextInt();
                    for (int i =0;i<names.length;i++){
                        if (names[i]!=null &&states[i]==0 && i ==deleteNo-1){
                            // 有订单信息，订单状态为已预订，是用户查找的编号
                            // 不能删除
                            isDelete = true;
                            System.out.println("您选择的订单未完成，不能删除！");
                        }else if(names[i]!=null &&states[i]==1 && i ==deleteNo-1) {
                            // 订单状态为已完成，能删除
                            isDelete = true;
                            //找到删除订单的位置 i   i后元素依次前移  最后一关置空
                            for (int j = i ;j<names.length-1;j++){
                                names[j] = names[j+1];
                                dishMsg[j] = dishMsg[j+1];
                                times[j] = times[j+1];
                                address[j] = address[j+1];
                                sumPrices[j] = sumPrices[j+1];
                                states[j] = states[j+1];
                            }
                            names[names.length-1] = null;
                            dishMsg[names.length-1] = null;
                            times[names.length-1] = 0;
                            address[names.length-1] = null;
                            sumPrices[names.length-1] = 0;
                            states[names.length-1] = 0;

                            System.out.println("删除订单成功！");
                        }
                    }
                    if (!isDelete){
                        System.out.println("您选择的订单不存在！不能删除");
                    }
                    break;
                case 5:
                    System.out.println("\n--------我要点赞--------");
                    // 显示菜品信息
                    System.out.println("序号\t菜名\t单价");
                    for (int j = 0; j < disNames.length; j++) {
                        String praise = (praiseNums[j]==0)?"":praiseNums[j]+"赞";
                        System.out.println((j + 1) + "\t" + disNames[j] + "\t" + price[j] + "\t" + praise);
                    }
                    // 点赞
                    System.out.print("请输出您要点赞的菜品：");
                    int praiseNo = in.nextInt();
                    while (praiseNo<1 || praiseNo>disNames.length){
                        System.out.println("本店没有这个菜品，请重新输入：");
                    praiseNo = in.nextInt();
                    }
                    praiseNums[praiseNo-1]++;
                    break;
                default:
                    flag = true;
                    //退出系统
            }
            if (!flag) { // !flag 等于 flag ==false
                System.out.println("请输入0返回：");
                num = in.nextInt();
            } else {
                break;
            }
        } while (num == 0);
        System.out.println("谢谢使用，欢迎下次光临！");
    }
}
