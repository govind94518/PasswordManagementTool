package com.epam.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({ @NamedQuery(name = "get List of GroupNames", query = "Select g.groupName from Group g"),
        @NamedQuery(name = "get List of Group", query = "Select g from Group g") })
@Table(name="Group_Table")
public class Group {

    @Id
    @Column(name = "Group_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    private  String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)

             List<Account>userAccounts;

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<Account> userAccounts) {
            userAccounts.forEach( userAccount->{
                userAccount.setGroup(this);
            });
            this.userAccounts = userAccounts;
        }


    public String getGroupName () {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
