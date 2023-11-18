import { Box, Paper } from "@mui/material";
import Image from "next/image";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export default function PersonaLiveComponent() {

    const socket = new SockJS("")
    const socketConnection = Stomp.over(socket);

    socketConnection.connect({}, function (frame) {
        console.log('Connected to SockJS and Stomp');
        socketConnection.subscribe('/topic/example', function (message) {
          console.log('Received message:', message.body);
        });
    })


    return (
        <Paper elevation={2}  sx={{ p: 2, height: '100%' }}>
            <Box style={{width: '100%', height: '100%', position: 'relative' }}>
                    <Image
                        alt={"product"}
                        src={"/img/dolphin_doctor.jpg"}
                        layout='fill'
                        objectFit='contain'
                        style={{ borderRadius: 5 }}
                    />
            </Box>

        </Paper>
    );
}
