"use client"
import {Box, IconButton, LinearProgress, Paper, Typography} from "@mui/material";
import QuestionInput from "@/components/product-questions/question-input/questionInput";
import HelpIcon from "@mui/icons-material/Help";
import {useEffect, useState} from "react";
import {Media} from "@/app/auctions/add-auction/page";
import axios from "axios";


export type QuestionDTO = {
  language: string,
  auctionId: number,
  question: string
}

export type QuestionResponseDTO = {
  translatedResponse: string,
  media: Media
}


export default function ProductQuestions(props: any) {
  const [chatMessages, setChatMessages] = useState<string[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [currentText, setCurrentText] = useState("");
  const [media, setMedia] = useState<Media | null>(null);


    useEffect(() => {

    }, [media?.imageUrl]);

  function updateChatMessageAndPlay() {
      setLoading(true);
      const data: QuestionDTO = {
        language: 'english',
        auctionId: Number(props.auctionId),
        question: currentText
      }

      axios.post<QuestionResponseDTO>("http://localhost:8080/api/question", data)
          .then(result => result.data)
          .then(result => {
            setLoading(false);
            setChatMessages(prevState => [...prevState, currentText, result.translatedResponse])
            setCurrentText('');
            props.setIsPlaying(true);
            setMedia(result.media);
          }).finally(() => {
            setLoading(false);
            setCurrentText('');
      })
  }

  function displayMessages() {
    const resultJsx = [];

    for(let i =0 ; i < chatMessages.length; ++i) {
      const message = chatMessages[i];

      if(i % 2 != 0) {
          resultJsx.push(<p>System : {message}</p>)
      } else {
        resultJsx.push(<p>Client : {message}</p>)
      }
    }

    return resultJsx;
  }

  return (
    <Paper elevation={2} sx={{ p: 3 }}>
      <Box>
        <Box display={"flex"} alignItems={"center"}>
          <IconButton>
            <HelpIcon />
          </IconButton>
          <Typography variant={"h6"} fontWeight={"bold"}>
            Ask about product
          </Typography>
            <audio className={'mx-2'} controls autoPlay={true} src={media?.imageUrl} onEnded={() => props.setIsPlaying(false)}/>
        </Box>
        <Typography ml={3} sx={{ color: "grey" }} mb={1}>
          {chatMessages.length === 0 ? <p>No messages asked</p> : displayMessages()}
        </Typography>
      </Box>

      {loading ? <LinearProgress/> :
      <QuestionInput onClick={updateChatMessageAndPlay} setCurrentValue={setCurrentText} value={currentText}/>}
    </Paper>
  );
}
