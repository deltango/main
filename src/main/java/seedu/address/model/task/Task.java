package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Task in Task Manager
 */
public class Task implements ReadOnlyTask {

    private static final String DEFAULT_DESCRIPTION = "";
    private static final IdentificationNumber DEFAULT_ID = IdentificationNumber.ZERO;

    private IdentificationNumber ID;
    private Name name;
    private Description description;
    private Deadline deadline;

    private UniqueTagList tags;

    /**
     * Name, ID, deadline are required and must not be null
     */
    public Task(Name name, Deadline deadline, Object... params) {
        assert !CollectionUtil.isAnyNull(name, deadline);

        this.ID = DEFAULT_ID;
        this.name = name;
        this.deadline = deadline;
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.tags = new UniqueTagList();

        // Optional parameters
        // ID tends to be set after Task creation,
        // so it is also included in optional params
        for (Object param : params) {
            if (param instanceof Description) {
                this.description = (Description) param;

            } else if (param instanceof UniqueTagList) {
                this.tags.mergeFrom((UniqueTagList) param);

            } else if (param instanceof Tag) {
                this.tags.add((Tag) param);

            } else if (param instanceof IdentificationNumber) {
                this.ID = (IdentificationNumber) param;
            }
        }
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getDeadline(), source.getID(), source.getDescription());
    }

    /**
     * Getters and setters
     */
    public void setID(IdentificationNumber ID) {
        this.ID = ID;
    }

    @Override
    public IdentificationNumber getID() {
        return ID;
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    public void setDeadline(Deadline deadline) {
        assert deadline != null;
        this.deadline = deadline;
    }

    @Override
    public Deadline getDeadline() {
        return deadline;
    }

    public void setDescription(Description description) {
        if (description == null) {
            this.description = new Description(DEFAULT_DESCRIPTION);
        } else {
            this.description = description;
        }
    }

    @Override
    public Description getDescription() {
        return description;
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setDeadline(replacement.getDeadline());
        this.setDescription(replacement.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, description);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}