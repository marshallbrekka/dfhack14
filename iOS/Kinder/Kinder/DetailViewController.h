//
//  DetailViewController.h
//  Kinder
//
//  Created by Klein Lieu on 10/11/14.
//  Copyright (c) 2014 Klein Lieu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController

@property (strong, nonatomic) id detailItem;
@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;

@end
