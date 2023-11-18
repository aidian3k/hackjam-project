import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import {Dispatch, FC, SetStateAction} from "react";
import {tag} from "postcss-selector-parser";

export const AuctionCard: FC<{
    title: string;
    description: string;
    imgUrl: string;
    tags: string[];
 }> = (props) => {
  return (
        <div
            className="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl cursor-pointer hover:scale-105"
            // onClick={() => props.openGoalDetails?.(props.goal ?? null)}
        >
          <div className="md:flex">
            <div className="md:shrink-0">
              <img
                  className="h-24 w-full object-cover md:h-full md:w-24"
                  src={props.imgUrl}
                  alt=""
              />
            </div>
            <div className="p-8">
              <div className="uppercase tracking-wide text-sm text-indigo-500 font-semibold">
                {props.title}
              </div>
              <a
                  href="#"
                  className="block mt-1 text-lg leading-tight font-medium text-black hover:underline"
              >

                 {/* {props.tags.map((tag) => (*/}
                 {/*   <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2">*/}
                 {/*       #{tag}*/}
                 {/*   </span>*/}
                 {/*))}*/}
              </a>
              <p className="mt-2 text-slate-500">{props.description.substring(0,100)}...</p>
            </div>
          </div>
        </div>
  );
}