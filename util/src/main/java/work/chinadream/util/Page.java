package work.chinadream.util;

/**
 *              分页基本休息
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/14 20:58
 */
public class Page {

    private int page = 1;//当前页码

    private int rows ;//每页显示数量

    private int offset;//对应数据库中偏移量


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        this.offset = (page-1)*rows;
        return offset;
    }

    public void setOffset(int offset) {

        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", rows=" + rows +
                ", offset=" + offset +
                '}';
    }
}
