/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if(size<maxSize){
            tracks[size++]=track;
            return true;
        }

        return false;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<tracks.length;i++){
            sb.append(tracks[i].toString());
            if(i!=tracks.length-1)
            sb.append("\n");
        }

        return sb.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
      if(size!=0)
      tracks[--size]=null;
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int seconds=0;
        for(int i=0;i<size;i++){
         seconds+=tracks[i].getDuration();
        }
        return seconds;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for(int i=0;i<size;i++){
          if(tracks[i].getTitle()==title)
          return i;
        }

        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if(i<0 || i>=size || size==maxSize)
        return false;
        Track lastTrack=tracks[i];
        tracks[i]=track;
        for(int k=i+1;k<size+1;k++){
            Track temp=tracks[k];
            tracks[k]=lastTrack;
            lastTrack=temp;
        }
        return true;

    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if(size!=0 && i>0 && i<maxSize){
            for(int x=i;x<size-1;x++){
                tracks[x]=tracks[i+1];
              }
              tracks[size-1]=null;
        }
         
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        for(int i=0;i<size;i++){
            if(tracks[i].getTitle()==title && i==size-1){
             tracks[i]=null;
             size--;
            }
            else if(tracks[i].getTitle()==title){
                for(int k=i;k<size-1;k++)
                tracks[k]=tracks[k+1];
                size--;
            }
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if (size!=0){
         tracks[0]=null;
         size--;
        }
       
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        int total=size+other.getSize();
        if(total<=maxSize){
            for(int i=size;i<total;i++){
                int k=0;
                tracks[i]=other.getTrack(k);
                k++;
            }
            size=total;
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if(start<0 || start>size-1)
        return -1;
        int min=100;
        int minI=0;
        for(int i=start;i<size;i++){
            if(tracks[i].getDuration()<min){
                min=tracks[i].getDuration();
                minI=i;
            }
        }
        return minI;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        if(size==0){return null;}
        int minI=minIndex(0);
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
      Track[] nTracks=new Track[size];
      for(int i=0;i<size;i++){
        nTracks[i]=tracks[minIndex(0)];
        remove(minIndex(0));
       
      }
        

    }
}
