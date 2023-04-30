package entity;

public enum HomePageHeader {
    TITLE("Welcome to ___ĐĂNG XUẤT KHỎI TRÁI ĐẤT___ flight booking!");
    private String title;

    HomePageHeader(String title) {
        this.title = title;
    }

    public String getTitleValue() {
        return title;
    }
}
