package vn.edu.iuh.fit.week1.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "log")
@NamedQueries({
        @NamedQuery(name = "Log.findByAccountIdOrderByLogoutTimeAsc", query = "select l from Log l where l.accountId = :accountId order by l.logoutTime"),
        @NamedQuery(name = "Log.deleteByNotes", query = "delete from Log l where l.notes = :notes"),
        @NamedQuery(name = "Log.updateNotesByAccountId", query = "update Log l set l.notes = :notes where l.accountId = :accountId")
})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Column(name = "login_time", nullable = false)
    private Date loginTime;

    @Column(name = "logout_time", nullable = false)
    private Date logoutTime;

    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Log() {
    }

    public Log(Long id, String accountId, Date loginTime, Date logoutTime, String notes) {
        this.id = id;
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}