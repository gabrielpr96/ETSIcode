
if server.NumDatagramsAvailable > 0
    datagram = read(server, server.NumDatagramsAvailable, "uint8");
    for s=1 : length(datagram)
        switch datagram(s, 1)
            case 0
                toc(pingT);
            case 1
                new_could = zeros(1, length(datagram(s))/4);
                j = 1;
                k = 2;
                while k < length(datagram(s))
                    new_could(j) = [datagram(s, k)+datagram(s, k+1)*256 datagram(s, k+2)+datagram(s, k+3)*256];
                    j = j + 1;
                    k = k + 4;
                end
                map_updated = true;
        end
    end
end
        