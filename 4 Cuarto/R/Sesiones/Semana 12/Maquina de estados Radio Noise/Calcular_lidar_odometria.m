
new_map_downsample = pcdownsample(new_map, 'gridAverage', 0.1);
tform = pcregisterndt(new_map_downsample, last_map, 0.5);
pos = tform*[x y 0 1];
x = pos(1);
y = pos(2);