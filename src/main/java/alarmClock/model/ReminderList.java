package alarmClock.model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created by pascal on 5/8/17.
 * List of Reminders who is observable has the ground ground funcionality, that can be used to implement undo /redo.
 * this is basically a Wrapper around the observable reminders list.
 */
public class ReminderList implements ObservableList {
    ObservableList reminders;
    private Stack<ObservableList> history;
    private Stack<ObservableList> undoneHistory;

    /**
     * constructor
     * initialises the history and undo history that are used for the undo /redo.
     * initialises  the Reminderlist.
     */
    public ReminderList() {
        history = new Stack<>();
        undoneHistory = new Stack<>();
        reminders = FXCollections.observableArrayList();
    }

    /*
    public ReminderList(ObservableList<Reminder> r) {
        this.reminders = r;
    }
    */


    /**
     * constructor
     * initialises the history and undo history that are used for the undo /redo.
     * initialises  the Reminderlist.
     * @param r takes a list of Observable reminders. to wrap this ReminderList around it.
     */

    public ReminderList(ObservableList<Reminder> r) {
        this.reminders = r;
        history = new Stack<>();
        undoneHistory = new Stack<>();
    }

    /**
     * pushes the current state to the history every time ReminderList is changed.
     */
    private void pushState() {
        ObservableList<Reminder> old = FXCollections.observableArrayList();
        old = reminders;
        /*
        for (Object o :reminders){
            if (o instanceof  Reminder){
                Reminder r = (Reminder) o;
                old.add(r);
            }
        }
        */
        history.push(old);

    }

    /**
     * pops the last state to enable the undo function.
     * @return  a observable list of reminders, who represents the last state.
     */
    private ObservableList popState() {
        ObservableList<Reminder> newReminder = FXCollections.observableArrayList();
        if (history.peek() != null) {
            ObservableList rs = history.pop();
            undoneHistory.push(rs);
            /*
            System.out.print(reminders);
            for (Object o :rs){
                if (o instanceof  Reminder){
                    Reminder r = (Reminder) o;
                    newReminder.add(r);
                }
            }
            */
            return newReminder;
        } else {
            return reminders;
        }
    }

    /**
     * the undo functionality, that resets the state of the wrapped reminderlist to the the last state.
     */
    public void undo() {
            /*
        System.out.println("Reminder.undo() is called");
        reminders = popState();
        System.out.println(r.toString());
        for(Reminder r = reminders)
            System.out.println(r.toString());
            */
    }

    /**
     * the redo function that resets the undo operation.
     */
    public void redo() {
        if (undoneHistory.peek() != null) {
            //ObservableList rs = undoneHistory.pop();
            ObservableList rs = popState();
            //history.push(rs);
            this.reminders = rs;
        }
    }



    /**
     * Add a listener to this observable list.
     *
     * @param listener the listener for listening to the list changes
     */
    @Override
    public void addListener(ListChangeListener listener) {
        reminders.addListener(listener);
    }

    /**
     * Tries to removed a listener from this observable list. If the listener is not
     * attached to this list, nothing happens.
     *
     * @param listener a listener to remove
     */
    @Override
    public void removeListener(ListChangeListener listener) {
        reminders.removeListener(listener);
    }

    /**
     * A convenient method for var-arg adding of elements.
     *
     * @param elements the elements to add
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean addAll(Object[] elements) {
        pushState();
        boolean r = reminders.addAll(elements);
        return r;
    }

    /**
     * Clears the ObservableList and add all the elements passed as var-args.
     *
     * @param elements the elements to set
     * @return true (as specified by Collection.add(E))
     * @throws NullPointerException if the specified arguments contain one or more null elements
     */
    @Override
    public boolean setAll(Object[] elements) {
        pushState();
        boolean r = reminders.setAll(elements);
        return r;
    }

    /**
     * Clears the ObservableList and add all elements from the collection.
     *
     * @param col the collection with elements that will be added to this observableArrayList
     * @return true (as specified by Collection.add(E))
     * @throws NullPointerException if the specified collection contains one or more null elements
     */
    @Override
    public boolean setAll(Collection col) {
        pushState();
        boolean r = reminders.setAll(col);
        return r;
    }

    /**
     * A convenient method for var-arg usage of retain method.
     *
     * @param elements the elements to be retained
     * @return true if list changed as a result of this call
     */
    @Override
    public boolean removeAll(Object[] elements) {
        pushState();
        boolean r = reminders.removeAll(elements);
        return r;
    }

    /**
     * A convenient method for var-arg usage of retain method.
     *
     * @param elements the elements to be retained
     * @return true if list changed as a result of this call
     */
    @Override
    public boolean retainAll(Object[] elements) {
        boolean r = reminders.retainAll(elements);
        pushState();
        return r;
    }

    /**
     * Basically a shortcut to sublist(from, to).clear()
     * As this is a common operation, ObservableList has this method for convenient usage.
     *
     * @param from the start of the range to remove (inclusive)
     * @param to   the end of the range to remove (exclusive)
     * @throws IndexOutOfBoundsException if an illegal range is provided
     */
    @Override
    public void remove(int from, int to) {
        pushState();
        reminders.remove(from, to);
    }


    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return reminders.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o) {
        return reminders.contains(o);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator iterator() {
        return reminders.iterator();
    }

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return reminders.size();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     * <p>
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     * <p>
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
    @Override
    public Object[] toArray() {
        return reminders.toArray();
    }


    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     * <p>
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param o object to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(Object o) {
        boolean r = reminders.add(o);
        pushState();
        return r;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     */
    @Override
    public boolean remove(Object o) {
        boolean r = reminders.remove(o);
        pushState();
        return r;
    }


    /**
     * A convenient method for var-arg adding of elements.
     *
     * @param c the elements to add
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean addAll(Collection c) {
        boolean r = reminders.addAll(c);
        pushState();
        return r;
    }

    /**
     * TODO JAVADOC
     *
     * @param index
     * @param c
     * @return
     */
    @Override
    public boolean addAll(int index, Collection c) {
        boolean r = reminders.addAll(index, c);
        pushState();
        return r;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this list
     */
    @Override
    public void clear() {
        reminders.clear();
        pushState();
    }

    /**
     * TODO JAVADOC
     *
     * @return
     */
    public ArrayList<Reminder> getSerializable() {
        //TODO solve type conversation on a better place
        ArrayList<Reminder> output = new ArrayList<>();
        for (Object r : reminders) {
            r = r;
            output.add((Reminder) r);
        }
        return output;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Object get(int index) {
        return reminders.get(index);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>set</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Object set(int index, Object element) {
        Object o = reminders.set(index, element);
        pushState();
        return o;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, Object element) {
        reminders.addAll(index, element);
        pushState();
    }

    /**
     * remove method to remove reminders from thw wrapped reminder list.
     * @param index to the Reminder that will be removed.
     * @return Rerminder that was at the index.
     */
    @Override
    public Object remove(int index) {
        Object o = reminders.remove(index);
        pushState();
        return o;
    }

    /**
     *
     * @param o Reminder forom which the index is returned.
     * @return an int that is the index of the passed reminder.
     */
    @Override
    public int indexOf(Object o) {
        return reminders.indexOf(o);
    }


     /** From String Javadoc:
     * Returns the index within this string of the last occurrence of
     * the specified character. For values of {@code ch} in the
     * range from 0 to 0xFFFF (inclusive), the index (in Unicode code
     * units) returned is the largest value <i>k</i> such that:
     * <blockquote><pre>
     * this.charAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true. For other values of {@code ch}, it is the
     * largest value <i>k</i> such that:
     * <blockquote><pre>
     * this.codePointAt(<i>k</i>) == ch
     * </pre></blockquote>
     * is true.  In either case, if no such character occurs in this
     * string, then {@code -1} is returned.  The
     * {@code String} is searched backwards starting at the last
     * character.
     *
     * @param   ch   a character (Unicode code point).
     * @return  the index of the last occurrence of the character in the
     *          character sequence represented by this object, or
     *          {@code -1} if the character does not occur.
    /**
     *
     * @param o O
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        return reminders.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return reminders.listIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        return reminders.listIterator(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return reminders.subList(fromIndex, toIndex);
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean b = reminders.retainAll(c);
        pushState();
        return b;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean b = reminders.removeAll(c);
        pushState();
        return b;
    }

    @Override
    public boolean containsAll(Collection c) {
        return reminders.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return reminders.toArray();
    }

    //TODO refactor until EOF
    public void addOwnListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }

    public void addOwnListener(ListChangeListener listener) {
        reminders.addListener(listener);
    }


    public void removeOwnListener(InvalidationListener listener) {
        reminders.removeListener(listener);
    }

    public void removeOwnListener(ListChangeListener listener) {
        reminders.removeListener(listener);
    }

    /**
     * Add a listener to this observable list.
     *
     * @param listener the listener for listening to the list changes
     */
    @Override
    public void addListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }

    /**
     * Tries to removed a listener from this observable list. If the listener is not
     * attached to this list, nothing happens.
     *
     * @param listener a listener to remove
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReminderList that = (ReminderList) o;

        return reminders != null ? reminders.equals(that.reminders) : that.reminders == null;
    }

    @Override
    public int hashCode() {
        return reminders != null ? reminders.hashCode() : 0;
    }
}
