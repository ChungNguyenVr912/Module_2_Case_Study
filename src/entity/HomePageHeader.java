package entity;

public enum HomePageHeader {
    TITLE("\uD83C\uDF0FWelcome to ___ĐĂNG XUẤT KHỎI TRÁI ĐẤT___ flight booking! \uD83D\uDE80");
    private final String title;

    HomePageHeader(String title) {
        this.title = title;
    }

    public String getTitleValue() {
        return title;
    }
}
