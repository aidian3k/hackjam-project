import { Box, Paper } from "@mui/material";
import Image from "next/image";
import {over, Client} from 'stompjs'
import SockJS from "sockjs-client"

var socketConnection : Client;
export default function PersonaLiveComponent() {

    const socket = new SockJS("http://http://localhost:3000/ws")
    socketConnection = over(socket)

    const onConnect = () => {
        socketConnection.subscribe('/topic/message', (payload) => {
            let payloadData=JSON.parse(payload.body);
            console.log(payloadData);
            
        })
    }

    const onError = () => {
        console.log("Error");
        
    }

    socketConnection.connect({}, onConnect, onError);



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
