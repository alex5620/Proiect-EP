package sample.TVPackages;

import sample.ChannelsPackage.ChannelData;

import java.util.List;

public class ChannelsSorter {
    public static void insertionSort(List<ChannelData> channels) {
        if (channels != null) {
            ChannelData temp;
            int i, size = channels.size();
            for (int k = 1; k < size; ++k) {
                i = k - 1;
                temp = channels.get(k);
                while ((i >= 0) && (temp.compareTo(channels.get(i)) < 0)) {
                    i = i - 1;
                }
                if (i != (k - 1)) {
                    channels.remove(temp);
                    channels.add(i + 1, temp);
                }
            }
        }
    }
}
